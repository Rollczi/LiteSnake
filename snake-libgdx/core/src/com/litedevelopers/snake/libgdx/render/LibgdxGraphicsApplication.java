package com.litedevelopers.snake.libgdx.render;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.libgdx.LibgdxPlayerInteraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LibgdxGraphicsApplication extends Game {

	private final GameSettings settings;
	private final LibgdxPlayerInteraction playerInteraction;

	Viewport viewport;
	Texture background;
	OrthographicCamera camera;
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

	Map<GraphicsElement.Type, CustomTexture> elementsTextures;
	final List<LibgdxElement> elements = Collections.synchronizedList(new ArrayList<>());

	SnakeMap map = SnakeMap.CLOSED;

	public LibgdxGraphicsApplication(GameSettings settings, LibgdxPlayerInteraction playerInteraction) {
		this.settings = settings;
		this.playerInteraction = playerInteraction;
	}

	@Override
	public void create() {
		background = new Texture("jungle.png");

		camera = new OrthographicCamera();
		camera.position.set(playerInteraction.getCamera());
		viewport = new FillViewport(settings.cameraWidth(), settings.cameraHeight(), camera);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		elementsTextures = Map.of(
				GraphicsElement.Type.SNAKE_HEAD, CustomTexture.of("snake_head.png", 1.0F),
				GraphicsElement.Type.SNAKE_BODY, CustomTexture.of("snake_body.png"),
				GraphicsElement.Type.APPLE, CustomTexture.of("apple.png"),
				GraphicsElement.Type.COCONUT, CustomTexture.of("coconut.png")
		);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new CameraScrollController(viewport));

		Gdx.input.setInputProcessor(inputMultiplexer);

		Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));
		Cursor cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
		pixmap.dispose();
		Gdx.graphics.setCursor(cursor);
	}

	float angle = 0.0F;
	Vector2 touchPos = new Vector2();

	@Override
	public void render() {
		readInput();

		ScreenUtils.clear(1.0F, 1.0F, 1.0F, 1.0F);
		viewport.apply();

		renderMap();
		renderElements();

		camera.position.lerp(playerInteraction.getCamera(), 0.1F);
	}

	private void readInput() {
		touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		viewport.unproject(touchPos);

		playerInteraction.setDirection(new Position(touchPos.x, touchPos.y));
		playerInteraction.setBoosting(Gdx.input.isKeyPressed(Input.Keys.SPACE));
	}


	private void renderElements() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		synchronized (elements) {
			for (LibgdxElement element : elements) {

				CustomTexture customTexture = elementsTextures.get(element.type);
				Texture texture = customTexture.texture;
				Sprite glowSprite = new Sprite(texture);

				float multi = element.rectangle.width / glowSprite.getWidth();

				glowSprite.setSize(glowSprite.getWidth() * multi, glowSprite.getHeight() * multi);
				glowSprite.setScale(customTexture.size);
				glowSprite.setRotation(element.rotation - 90);
				glowSprite.setOriginCenter();
				glowSprite.setPosition(element.rectangle.x, element.rectangle.y);

				glowSprite.draw(batch);
			}
		}

		batch.end();
	}

	private void renderMap() {
		if (!map.isClosed()) {
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(Color.LIGHT_GRAY);


			for (int x = (int) map.getMin().getX(); x < map.getMax().getX(); x += 25) {
				shapeRenderer.line(new Vector2((float) x, (float) map.getMin().getY()), new Vector2((float) x, (float) map.getMax().getY()));
			}

			for (int y = (int) map.getMin().getY(); y < map.getMax().getY(); y += 25) {
				shapeRenderer.line(new Vector2((float) map.getMin().getX(), (float) y), new Vector2((float) map.getMax().getX(), (float) y));
			}

			shapeRenderer.line(new Vector2((float) map.getMax().getX(), (float) map.getMin().getY()), new Vector2((float) map.getMax().getX(), (float) map.getMax().getY()));
			shapeRenderer.line(new Vector2((float) map.getMin().getX(), (float) map.getMax().getY()), new Vector2((float) map.getMax().getX(), (float) map.getMax().getY()));
			shapeRenderer.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	void addElement(LibgdxElement element) {
		elements.add(element);
	}

	void removeElement(LibgdxElement element) {
		elements.remove(element);
	}

	private static class CustomTexture {
		private final Texture texture;
		private final float size;

		private CustomTexture(Texture texture, float size) {
			this.texture = texture;
			this.size = size;
		}

		private static CustomTexture of(String name, float size) {
			return new CustomTexture(new Texture(name), size);
		}

		private static CustomTexture of(String name) {
			return new CustomTexture(new Texture(name), 1.0F);
		}

	}

}
