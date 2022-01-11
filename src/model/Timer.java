package model;

import controller.Controller;
import static java.lang.Thread.sleep;

public class Timer {

    private int seconds, minutes, hours;
    private boolean running = false;

    public void startTimer(Controller controller) {

        running = true;

        new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {

                        sleep(1000);

                        if (seconds >= 60) {
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes >= 60) {
                            seconds = 0;
                            minutes = 0;
                            hours++;
                        }

                        controller.setTaskDuration(String.format("%02d : %02d : %02d", hours, minutes, seconds));

                        seconds++;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }

    public void stopTimer() {

        running = false;

    }

    public void resetTimer(Controller controller) {

        running = false;

        seconds = 0;
        minutes = 0;
        hours = 0;

        controller.setTaskDuration(String.format("%02d : %02d : %02d", hours, minutes, seconds));

    }

}
