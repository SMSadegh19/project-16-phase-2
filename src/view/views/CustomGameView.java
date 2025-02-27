package view.views;

import contracts.CustomGameContract;
import models.Deck;
import view.MenuHandler;

import java.util.ArrayList;

import static view.menuItems.MenuConstants.IN_GAME_MENU;

public class CustomGameView implements CustomGameContract.View {
    private CustomGameContract.Controller controller;

    @Override
    public void setController(CustomGameContract.Controller controller) {
        this.controller = controller;
    }

    @Override
    public void goToInGameMenu() {
        //MenuHandler.goToSubMenu(IN_GAME_MENU);
    }

    @Override
    public void showDecks(ArrayList<Deck> decks) {
        CollectionView collectionView = new CollectionView();
        for (Deck deck : decks) {
            collectionView.showDeck(deck);
        }
    }
}
