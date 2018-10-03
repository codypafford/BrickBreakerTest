package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.beans.property.*;
import java.util.*;
import javafx.animation.Animation;
import javafx.animation.*;
import javafx.event.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;






public class Main extends Application {
    final int movement = 3;
    List bricks = new ArrayList();
    public static Circle circle;


    @Override
    public void start(final Stage stage) throws Exception {


        final Canvas canvas = new Canvas(810, 875);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        final DrawableRectangle rect = new DrawableRectangle(340, 740, 100, 20, canvas);
        circle = new Circle(15, Color.BLUE);
        circle.relocate(100, 100);

        double deltaX = 3;
        double deltaY = 3;

        Button btn = new Button("btn");



        //Creating the Bounds
        final Bounds bounds = canvas.getBoundsInLocal();
        final boolean atRightBorder = rect.getLayoutX() >= (bounds.getMaxX() - rect.getX());
        final boolean atLeftBorder = rect.getLayoutX() <= (bounds.getMinX() + rect.getX());
        final boolean atBottomBorder = rect.getLayoutY() >= (bounds.getMaxY() - rect.getX());
        final boolean atTopBorder = rect.getLayoutY() <= (bounds.getMinY() + rect.getX());


        //Draw Paddle
        rect.draw();


        final Pane root = new Pane();
        root.getChildren().addAll( canvas, circle, btn);


        //Handle the Key Codes
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.LEFT && rect.getX() > 0) {
                    rect.setX(rect.getX() - 25);
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    rect.draw();
                    drawShapes(gc, canvas);

                } else if (ke.getCode() == KeyCode.RIGHT && rect.getX() < 710) {
                    rect.setX(rect.getX() + 25);
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    rect.draw();
                    drawShapes(gc, canvas);
                }

            }
        });



        drawShapes(gc, canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Brick Breaker");
        stage.show();
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            double deltaX = 3;
            double deltaY = 3;

            @Override
            public void handle(final ActionEvent t) {
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);

                final Bounds bounds = canvas.getBoundsInLocal();
                final boolean atRightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                final boolean atLeftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                final boolean atBottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                final boolean atTopBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());
                final boolean atPaddle = circle.getLayoutY() <= (rect.getLayoutY()  - circle.getRadius());

                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
                if (atPaddle){
                    deltaY *= -1;

                }
            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();







    }


    public static void main(String[] args) {
        launch(args);
    }

    public void drawShapes(GraphicsContext gc, Canvas canvas) {
        for (int x = 5; x < 800; x += 35) {
            DrawableSquare square;
            square = new DrawableSquare(x, 5, 30, 30, canvas);
            bricks.add(square);
            square.draw();


            for (int y = 5; y < 300; y += 35) {
                square = new DrawableSquare(x, y, 30, 30, canvas);
                bricks.add(square);
                square.draw();
            }

        }


    }
}

    class DrawableRectangle extends Rectangle {
        Canvas theCanvas;
        boolean returnedValue = false;


        public DrawableRectangle(Rectangle r, Canvas c) {
            super(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            this.theCanvas = c;
        }

        public DrawableRectangle(int x, int y, int w, int h, Canvas c) {
            super(x, y, w, h);
            this.theCanvas = c;
        }

        public void draw(Color color) {
            GraphicsContext gc = theCanvas.getGraphicsContext2D();

            gc.setFill(Paint.valueOf(String.valueOf(color)));

        }

        public void draw(){
            GraphicsContext gc = theCanvas.getGraphicsContext2D();

            gc.setFill(Color.BLUEVIOLET);
            gc.fillRect(getX(), getY(), getWidth(), getHeight());

        }


    }

class DrawableSquare extends Rectangle {
    Canvas theCanvas;

    public DrawableSquare(int x, int y, int w, int h, Canvas c){
        super(x,y,w,h);
        this.theCanvas = c;
    }

    public void draw(){
        GraphicsContext gc = theCanvas.getGraphicsContext2D();

        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(getX(), getY(), getWidth(), getHeight());

    }
}








