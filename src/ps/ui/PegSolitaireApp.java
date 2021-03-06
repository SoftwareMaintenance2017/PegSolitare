package ps.ui;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ps.engine.GameEngine;
import ps.engine.PrebuildBoard;
import ps.engine.model.Hole;
import ps.engine.model.PegMove;

public class PegSolitaireApp extends Application {

	private static final Logger LOGGER = Logger.getLogger(PegSolitaireApp.class.getName());

	static final int TILE_SIZE = 80;

	GameEngine game;
	private BoardTile[][] tilesBoard = new BoardTile[7][7];
	private Pane root = new Pane();

	BoardTile initialPosition;
	BoardTile finalPosition;

	private Label printArea;

	private Parent createContent() {
		root.setPrefSize(12 * TILE_SIZE, 9 * TILE_SIZE);
		root.setStyle("-fx-background-color: #ffffff;");

		LOGGER.info("Drawing board");

		game = new GameEngine();
		game.newGame();

		createPositionsIndexes();
		createBoardTiles();
		createButtons();
		createPrintArea();

		return root;
	}

	private void createPrintArea() {
		printArea = new Label("");
		printArea.setMaxWidth(TILE_SIZE * 3);
		printArea.setMaxHeight(TILE_SIZE * 5);
		printArea.setWrapText(true);
		printArea
				.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 20; -fx-text-fill: darkred;");
		printArea.setTranslateX(TILE_SIZE * 9);
		printArea.setTranslateY(TILE_SIZE * 3);
		root.getChildren().add(printArea);
	}

	private void createButtons() {
		Button restartButton = new Button("Restart game");
		restartButton.setTranslateX(TILE_SIZE * 9);
		restartButton.setTranslateY(TILE_SIZE);
		restartButton.setOnAction(actionEvent -> {
			game.newGame();
			Platform.runLater(this::redrawMap);
		});

		Button undoButton = new Button("Undo move");
		undoButton.setTranslateX(TILE_SIZE * 10);
		undoButton.setTranslateY(TILE_SIZE);
		undoButton.setOnAction(actionEvent -> {
			game.undoMove();
			Platform.runLater(this::redrawMap);
		});

		Button movesButton = new Button("Display moves");
		movesButton.setTranslateX(TILE_SIZE * 9);
		movesButton.setTranslateY(TILE_SIZE * 2);
		movesButton.setOnAction(actionEvent -> {
			displayMoves();
			Platform.runLater(this::redrawMap);
		});

		root.getChildren().addAll(restartButton, undoButton, movesButton);
	}

	private void createBoardTiles() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				BoardTile tile = new BoardTile(this, i, j);
				tile.setTranslateX((j + 1) * TILE_SIZE);
				tile.setTranslateY((i + 1) * TILE_SIZE);

				root.getChildren().add(tile);
				Hole hole = game.getBoard().getHoles()[i][j];
				if (hole.isEnabled()) {
					if (hole.hasPeg())
						tile.fill(Color.BLUEVIOLET);
					else
						tile.fill();
				}
				tilesBoard[i][j] = tile;
			}
		}
	}

	private void createPositionsIndexes() {
		for (int i = 0; i < 7; i++) {
			BoardTile hTile = new BoardTile(this);
			hTile.setTranslateX((i + 1) * TILE_SIZE);
			hTile.setTranslateY(0);
			hTile.fill(String.valueOf(i), Color.AQUAMARINE);
			hTile.getBoardBorder().setStroke(Color.WHITE);

			BoardTile vTile = new BoardTile(this);
			vTile.setTranslateX(0 * TILE_SIZE);
			vTile.setTranslateY((i + 1) * TILE_SIZE);
			vTile.fill(String.valueOf(i), Color.AQUAMARINE);
			vTile.getBoardBorder().setStroke(Color.WHITE);

			root.getChildren().addAll(hTile, vTile);
		}
	}

	private void displayMoves() {
		StringBuilder movementsBuilder = new StringBuilder("Movements:");
		for (PegMove move : game.getMoveHistory())
			movementsBuilder.append("\n").append(move);
		printArea.setText(movementsBuilder.toString());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
	}

	void validateMove() {
		LOGGER.info("Updating board with new move");
		game.movePeg(initialPosition.getPosition(), finalPosition.getPosition());
		redrawMap();
		initialPosition = null;
		finalPosition = null;

		if (game.isOver()) {
			LOGGER.info("GAME OVER");
			displaySuccessModal();
		}

	}

	private void displaySuccessModal() {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setTitle("Peg solitaire");

		Button restartButton = new Button("Display moves");
		restartButton.setOnAction(actionEvent -> {
			displayMoves();
			Platform.runLater(this::redrawMap);
			dialogStage.close();
		});

		Button historyButton = new Button("Other board");
		historyButton.setOnAction(actionEvent -> {
			game.newGame(PrebuildBoard.PYRAMID);
			Platform.runLater(this::redrawMap);
			dialogStage.close();
		});

		VBox vbox = new VBox(new Text("CONGRATULATIONS"), historyButton, restartButton);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(25));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	private void redrawMap() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				final BoardTile tile = tilesBoard[i][j];
				Hole hole = game.getBoard().getHoles()[i][j];
				if (hole.isEnabled())
					if (hole.hasPeg())
						tile.fill(Color.BLUEVIOLET);
					else
						tile.fill();
			}
		}
	}

}