/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package dominio;

import java.util.ArrayList;

public class Observable {

    private ArrayList<Observer> observadores;

    public Observable() {
        observadores = new ArrayList<>();
    }

    public void agregarObserver(Observer unObserver) {
        observadores.add(unObserver);
    }

    public void quitarObserver(Observer unObserver) {
        observadores.remove(unObserver);
    }

    public void notificarObservers() {
        for (Observer obs : observadores) {
            obs.actualizar();
        }
    }
}
