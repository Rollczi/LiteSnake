package com.litedevelopers.snake.libgdx.render;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.litedevelopers.snake.engine.GameSettings;
import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.libgdx.LibgdxPlayerInteraction;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LibgdxGraphicsApplication extends Game {

	private final GameSettings settings;
	private final LibgdxPlayerInteraction playerInteraction;

	Texture background;
	Viewport viewport;
	OrthographicCamera camera;
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;

	Map<GraphicsElement.Type, Texture> elementsTextures;
	Set<LibgdxElement> elements = ConcurrentHashMap.newKeySet();

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
				GraphicsElement.Type.SNAKE_HEAD, new Texture("snake_head.png"),
				GraphicsElement.Type.SNAKE_BODY, new Texture("snake_body.png"),
				GraphicsElement.Type.APPLE, new Texture("apple.png"),
				GraphicsElement.Type.COCONUT, new Texture("coconut.png")
		);
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

		camera.position.lerp(playerInteraction.getCamera(), 0.007F);
	}

	private void readInput() {

		touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		viewport.unproject(touchPos);

		playerInteraction.setDirection(new Position(touchPos.x, touchPos.y));


		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
		}
	}

	private void renderElements() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();


		for (LibgdxElement element : elements) {

			Texture texture = elementsTextures.get(element.type);
			Sprite glowSprite = new Sprite(texture);

			glowSprite.setSize(element.rectangle.width, element.rectangle.height);
			glowSprite.setOriginCenter();
			glowSprite.setRotation(element.rotation);
			glowSprite.setOriginBasedPosition(element.rectangle.x, element.rectangle.y);

			glowSprite.draw(batch);
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

}
