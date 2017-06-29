package ps.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ps.engine.GameEngine;
import ps.engine.model.Position;

public class PegSolitaireApp extends Application {

    private GameEngine game;
    private Tile[][] tilesBoard = new Tile[7][7];
    private Pane root = new Pane();

    private Tile initialPosition;
    private Tile finalPosition;

    private Parent createContent() {
	root.setPrefSize(700, 700);
	game = new GameEngine();

	for (int i = 0; i < 7; i++) {
	    for (int j = 0; j < 7; j++) {
		Tile tile = new Tile();
		tile.setTranslateX(j * 100);
		tile.setTranslateY(i * 100);
		tile.getPosition().setX(j);
		tile.getPosition().setY(i);
		root.getChildren().add(tile);
		if (game.getBoard().getHoles()[j][i].isEnabled())
		    if (game.getBoard().getHoles()[j][i].isHasPeg())
			tile.drawO(Color.BLUEVIOLET);
		    else
			tile.drawO();
		tilesBoard[j][i] = tile;
	    }
	}

	return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	primaryStage.setScene(new Scene(createContent()));
	primaryStage.show();
    }

    private void checkMove() {
	game.movePeg(initialPosition.getPosition(), finalPosition.getPosition());
	redrawMap();
	initialPosition = null;
	finalPosition = null;

	if (game.isOver()) {
	    Stage dialogStage = new Stage();
	    dialogStage.initModality(Modality.WINDOW_MODAL);

	    Button restartButton = new Button("Reiniciar");
	    VBox vbox = new VBox(new Text("FELICITACIONES"), restartButton);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(15));

	    restartButton.setOnAction(actionEvent -> {
		game = new GameEngine();
		Platform.runLater(() -> {
		    redrawMap();
		});
		dialogStage.close();
	    });

	    dialogStage.setScene(new Scene(vbox));
	    dialogStage.show();
	}

    }

    private void redrawMap() {
	for (int i = 0; i < 7; i++) {
	    for (int j = 0; j < 7; j++) {
		final Tile tile = tilesBoard[j][i];
		if (game.getBoard().getHoles()[j][i].isEnabled())
		    if (game.getBoard().getHoles()[j][i].isHasPeg())
			tile.drawO(Color.BLUEVIOLET);
		    else
			tile.drawO();
	    }
	}
    }

    private class Combo {
	private Tile[] tiles;

	public Combo(Tile... tiles) {
	    this.tiles = tiles;
	}

	public boolean isComplete() {
	    if (tiles[0].getValue().isEmpty())
		return false;

	    return tiles[0].getValue().equals(tiles[1].getValue()) && tiles[0].getValue().equals(tiles[2].getValue());
	}
    }

    private class Tile extends StackPane {
	private Text text = new Text();
	private Position position = new Position();

	public Tile() {
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
		    drawO(Color.RED);
		    if (initialPosition == null)
			initialPosition = this;
		    else {
			finalPosition = this;
			checkMove();
		    }
		}
	    });
	}

	public double getCenterX() {
	    return getTranslateX() + 100;
	}

	public double getCenterY() {
	    return getTranslateY() + 100;
	}

	public String getValue() {
	    return text.getText();
	}

	public void drawO() {
	    text.setFill(Color.BLACK);
	    text.setText("O");
	}

	public void drawO(Color color) {
	    text.setFill(color);
	    text.setText("O");
	}

	private Position getPosition() {
	    return position;
	}

    }

    public static void main(String[] args) {
	launch(args);
    }

    public GameEngine getGame() {
	return game;
    }

    public void setGame(GameEngine game) {
	this.game = game;
    }

    private Tile getInitialPosition() {
	return initialPosition;
    }

    private void setInitialPosition(Tile initialPosition) {
	this.initialPosition = initialPosition;
    }

    private Tile getFinalPosition() {
	return finalPosition;
    }

    private void setFinalPosition(Tile finalPosition) {
	this.finalPosition = finalPosition;
    }
}