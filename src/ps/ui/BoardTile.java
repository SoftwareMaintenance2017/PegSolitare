package ps.ui;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ps.engine.model.Position;

class BoardTile extends StackPane {
/**
	 * 
	 */
	private final PegSolitaireApp pegSolitaireApp;
private Text text = new Text();
private Position position = new Position();
private Rectangle boardBorder;

public BoardTile(PegSolitaireApp pegSolitaireApp) {
    this.pegSolitaireApp = pegSolitaireApp;
	boardBorder = new Rectangle(PegSolitaireApp.TILE_SIZE, PegSolitaireApp.TILE_SIZE);
    boardBorder.setFill(null);
    boardBorder.setStroke(Color.BLACK);
    text.setFont(Font.font(72 * PegSolitaireApp.TILE_SIZE / 100));
    setAlignment(Pos.CENTER);
    getChildren().addAll(boardBorder, text);
    setOnMouseClicked(event -> {
	if (event.getButton() == MouseButton.PRIMARY) {
	    if (!this.pegSolitaireApp.game.getBoard().getHoles()[position.getX()][position.getY()].isEnabled())
		return;
	    fill(Color.RED);
	    if (this.pegSolitaireApp.initialPosition == null)
		this.pegSolitaireApp.initialPosition = this;
	    else {
		this.pegSolitaireApp.finalPosition = this;
		this.pegSolitaireApp.validateMove();
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

Position getPosition() {
    return position;
}

Rectangle getBoardBorder() {
    return boardBorder;
}

}