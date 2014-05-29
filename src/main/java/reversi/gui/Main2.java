package reversi.gui;

import reversi.gui.TUI.BoardTUI2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jannik on 28.05.2014.
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Geben sie die Spielfeldgröße ein:");
        int size=Integer.valueOf(bufferRead.readLine());
        System.out.println("Wie viele Züge soll der Computer vorrausdenken?");
        int searchDepth=Integer.valueOf(bufferRead.readLine());
        System.out.println("Soll der AB Schnitt angewendet werden (Ja/Nein)?");
        boolean abEnabled=bufferRead.readLine().equalsIgnoreCase("Ja");
        System.out.println("Wollen Sie anfangen (Ja/Nein)?");
        boolean playerbegins=bufferRead.readLine().equalsIgnoreCase("Ja");
        System.out.println("Das Spiel beginnt: \n");
        new BoardTUI2(size,searchDepth, playerbegins, abEnabled);
    }
}
