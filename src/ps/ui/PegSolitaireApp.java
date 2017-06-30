package ps.ui;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private GameEngine game;
    private BoardTile[][] tilesBoard = new BoardTile[7][7];
    private Pane root = new Pane();

    private BoardTile initialPosition;
    private BoardTile finalPosition;

    private Parent createContent() {
	root.setPrefSize(700, 700);
	game = new GameEngine();

	LOGGER.info("Drawing board");
	for (int i = 0; i < 7; i++) {
	    for (int j = 0; j < 7; j++) {
		BoardTile tile = new BoardTile();
		tile.setTranslateX(i * 100);
		tile.setTranslateY(j * 100);
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

	return root;
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
	    Stage dialogStage = new Stage();
	    dialogStage.initModality(Modality.WINDOW_MODAL);

	    Button restartButton = new Button("Reiniciar");
	    VBox vbox = new VBox(new Text("FELICITACIONES"), restartButton);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(15));

	    restartButton.setOnAction(actionEvent -> {
		game = new GameEngine();
		Platform.runLater(this::redrawMap);
		dialogStage.close();
	    });

	    dialogStage.setScene(new Scene(vbox));
	    dialogStage.show();
	}

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

	public BoardTile() {
	    Rectangle border = new Rectangle(100, 100);
	    border.setFill(null);
	    border.setStroke(Color.BLACK);
	    text.setFont(Font.font(72));
	    setAlignment(Pos.CENTER);
	    getChildren().addAll(border, text);
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

	private Position getPosition() {
	    return position;
	}

    }

    public GameEngine getGame() {
	return game;
    }

    public void setGame(GameEngine game) {
	this.game = game;
    }
}