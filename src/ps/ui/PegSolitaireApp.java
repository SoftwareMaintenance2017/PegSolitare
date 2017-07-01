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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ps.engine.GameEngine;
import ps.engine.model.Position;

public class PegSolitaireApp extends Application {

    private static final Logger LOGGER = Logger.getLogger(PegSolitaireApp.class.getName());

    private static final int TILE_SIZE = 80;

    private GameEngine game;
    private BoardTile[][] tilesBoard = new BoardTile[7][7];
    private Pane root = new Pane();

    private BoardTile initialPosition;
    private BoardTile finalPosition;

    private Label printArea;

    private Parent createContent() {
	root.setPrefSize(12 * TILE_SIZE, 9 * TILE_SIZE);
	root.setStyle("-fx-background-color: #ffffff;");
	game = new GameEngine();

	LOGGER.info("Drawing board");
	createPositionsIndexes();
	createBoardTiles();
	createButtons();
	createPrintArea();

	return root;
    }

    private void createPrintArea() {
	Label printArea = new Label("");
	printArea.setMaxWidth(TILE_SIZE * 3);
	printArea.setMaxHeight(TILE_SIZE * 5);
	printArea.setWrapText(true);
	printArea.setStyle("-fx-font-family: \"Arial\"; -fx-font-size: 20; -fx-text-fill: darkred;");
	printArea.setTranslateX(TILE_SIZE * 9);
	printArea.setTranslateY(TILE_SIZE * 3);
	root.getChildren().add(printArea);
    }

    private void createButtons() {
	Button restartButton = new Button("Reiniciar");
	restartButton.setTranslateX(TILE_SIZE * 9);
	restartButton.setTranslateY(TILE_SIZE);
	restartButton.setOnAction(actionEvent -> {
	    game = new GameEngine();
	    Platform.runLater(this::redrawMap);
	});

	Button undoButton = new Button("Deshacer");
	undoButton.setTranslateX(TILE_SIZE * 10);
	undoButton.setTranslateY(TILE_SIZE);
	undoButton.setOnAction(actionEvent -> {
	    game = new GameEngine();
	    Platform.runLater(this::redrawMap);
	});

	Button movesButton = new Button("Mostrar jugadas");
	movesButton.setTranslateX(TILE_SIZE * 9);
	movesButton.setTranslateY(TILE_SIZE * 2);
	movesButton.setOnAction(actionEvent -> {
	    game = new GameEngine();
	    Platform.runLater(this::redrawMap);
	});

	root.getChildren().addAll(restartButton, undoButton, movesButton);
    }

    private void createBoardTiles() {
	for (int i = 0; i < 7; i++) {
	    for (int j = 0; j < 7; j++) {
		BoardTile tile = new BoardTile();
		tile.setTranslateX((i + 1) * TILE_SIZE);
		tile.setTranslateY((j + 1) * TILE_SIZE);
		tile.getPosition().setX(i);
		tile.getPosition().setY(j);
		root.getChildren().add(tile);
		if (game.getBoard().getHoles()[i][j].isEnabled())
		    if (game.getBoard().getHoles()[i][j].isHasPeg())
			tile.fill(Color.BLUEVIOLET);
		    else
			tile.fill();
		tilesBoard[i][j] = tile;
	    }
	}
    }

    private void createPositionsIndexes() {
	for (int i = 0; i < 7; i++) {
	    BoardTile hTile = new BoardTile();
	    hTile.setTranslateX((i + 1) * TILE_SIZE);
	    hTile.setTranslateY(0);
	    hTile.fill(String.valueOf(i), Color.ALICEBLUE);
	    hTile.getBoardBorder().setStroke(Color.WHITE);

	    BoardTile vTile = new BoardTile();
	    vTile.setTranslateX(0 * TILE_SIZE);
	    vTile.setTranslateY((i + 1) * TILE_SIZE);
	    vTile.fill(String.valueOf(i), Color.ALICEBLUE);
	    vTile.getBoardBorder().setStroke(Color.WHITE);

	    root.getChildren().addAll(hTile, vTile);
	}
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	primaryStage.setScene(new Scene(createContent()));
	primaryStage.show();
    }

    private void checkCurrentMove() {
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

	Button restartButton = new Button("Reiniciar");
	VBox vbox = new VBox(new Text("FELICITACIONES"), restartButton);
	vbox.setSpacing(20);
	vbox.setAlignment(Pos.CENTER);
	vbox.setPadding(new Insets(25));

	restartButton.setOnAction(actionEvent -> {
	game = new GameEngine();
	Platform.runLater(this::redrawMap);
	dialogStage.close();
	});

	dialogStage.setScene(new Scene(vbox));
	dialogStage.show();
    }

    private void redrawMap() {
	for (int i = 0; i < 7; i++) {
	    for (int j = 0; j < 7; j++) {
		final BoardTile tile = tilesBoard[i][j];
		if (game.getBoard().getHoles()[i][j].isEnabled())
		    if (game.getBoard().getHoles()[i][j].isHasPeg())
			tile.fill(Color.BLUEVIOLET);
		    else
			tile.fill();
	    }
	}
    }

    private class BoardTile extends StackPane {
	private Text text = new Text();
	private Position position = new Position();
	private Rectangle boardBorder;

	public BoardTile() {
	    boardBorder = new Rectangle(TILE_SIZE, TILE_SIZE);
	    boardBorder.setFill(null);
	    boardBorder.setStroke(Color.BLACK);
	    text.setFont(Font.font(72 * TILE_SIZE / 100));
	    setAlignment(Pos.CENTER);
	    getChildren().addAll(boardBorder, text);
	    setOnMouseClicked(event -> {
		if (event.getButton() == MouseButton.PRIMARY) {
		    if (!game.getBoard().getHoles()[position.getX()][position.getY()].isEnabled())
			return;
		    fill(Color.RED);
		    if (initialPosition == null)
			initialPosition = this;
		    else {
			finalPosition = this;
			checkCurrentMove();
		    }
		}
	    });
	}

	public void fill() {
	    text.setFill(Color.BLACK);
	    text.setText("O");
	}

	public void fill(Color color) {
	    text.setFill(color);
	    text.setText("O");
	}

	public void fill(String tileText, Color color) {
	    text.setFill(color);
	    text.setText(tileText);
	}

	private Position getPosition() {
	    return position;
	}

	private Rectangle getBoardBorder() {
	    return boardBorder;
	}

    }

    public GameEngine getGame() {
	return game;
    }

    public void setGame(GameEngine game) {
	this.game = game;
    }
}