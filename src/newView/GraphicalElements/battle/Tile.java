package newView.GraphicalElements.battle;

import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import models.card.Unit;
import newView.AnimationMaker;
import newView.GraphicalElements.ScaleTool;

import static newView.BattleView.GameGraphicListener.GAME_ACT_TIME;

public class Tile extends Pane {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public double getX() {
        return getLayoutX();// TODO: 6/14/19 may change
    }

    public double getY() {
        return getLayoutY();
    }

    private boolean isSelected = false;
    private TilePolygon polygon = new TilePolygon();
    private ImageView imageView;
    private Unit unit;
    public static final double NORMAL_OPACITY = 0.2;
    public static final double HOVER_OPACITY = 0.4;
    public static final double SELECTED_OPACITY = 0.6;
    public static final double TILE_LENGTH = 80;

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
        ScaleTool.relocateTile(this);
        ScaleTool.makeTilePoints(polygon, row, column);
        this.getChildren().add(polygon);
        setMouseEventsFor(polygon);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setImageView(ImageView imageView, Unit unit) {
        this.unit = unit;
        if (this.imageView != null) {
            this.getChildren().remove(this.imageView);
        }
        this.imageView = imageView;
        if (imageView != null) {
            ScaleTool.resizeImageView(imageView, TILE_LENGTH, TILE_LENGTH);
            this.getChildren().add(imageView);
            setMouseEventsFor(imageView);
        }
    }

    private void setMouseEventsFor(Node node) {
        node.setOnMouseEntered(event -> {
            if (!isSelected) {
                polygon.setOpacity(HOVER_OPACITY);
            }
        });
        node.setOnMouseExited(event -> {
            if (!isSelected) {
                polygon.setOpacity(NORMAL_OPACITY);
            }
        });
        node.setOnMouseClicked(event -> {
            isSelected = !isSelected;
            if (isSelected) {
                polygon.setOpacity(SELECTED_OPACITY);
            } else {
                polygon.setOpacity(HOVER_OPACITY);
            }
        });
    }

    public void enableColorAnimation(Color color) {
        KeyValue colorValue = new KeyValue(polygon.fillProperty(), color);
        KeyValue strokeValue = new KeyValue(polygon.strokeWidthProperty(), polygon.getStrokeWidth() * 3);
        Timeline timeline = AnimationMaker.makeTimeline(
                Duration.millis(GAME_ACT_TIME * 0.4)
                , true, 2
                , colorValue, strokeValue);
        timeline.play();
    }

    public void showSpellCast(ImageView spellView) {
        final double SPELL_SIZE = 50;
        final double START_SIZE = TILE_LENGTH / 2 - SPELL_SIZE / 2;
        ScaleTool.resizeImageView(spellView, SPELL_SIZE, SPELL_SIZE);
        ScaleTool.relocate(spellView, START_SIZE, START_SIZE);
        enableColorAnimation(Color.BLUE);
        this.getChildren().add(spellView);

        KeyValue keyValue = new KeyValue(spellView.xProperty(), spellView.getX() + 3);
        KeyValue keyValue1 = new KeyValue(spellView.yProperty(), spellView.getY() + 3);
        KeyValue keyValue2 = new KeyValue(spellView.rotateProperty(), spellView.getRotate() + 5);
        KeyValue keyValue3 = new KeyValue(spellView.scaleXProperty(), spellView.getScaleX() * 1.1);
        KeyValue keyValue4 = new KeyValue(spellView.scaleYProperty(), spellView.getScaleY() * 1.1);
        Timeline timeline = AnimationMaker.makeTimeline(Duration.millis(GAME_ACT_TIME * 0.06), true, 10
                ,keyValue, keyValue1, keyValue2, keyValue3, keyValue4);
        timeline.play();
        timeline.setOnFinished(event -> this.getChildren().remove(spellView));
    }
}
