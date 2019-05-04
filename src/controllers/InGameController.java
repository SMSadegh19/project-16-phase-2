package controllers;

import contracts.InGameContract;
import models.*;
import models.card.*;
import models.card.exception.*;
import view.Notify;
import view.views.InGameView;

import java.util.ArrayList;

public class InGameController implements InGameContract.Controller {
    private InGameContract.View view;

    public InGameController() {
        view = new InGameView();
        view.setController(this);
    }

    // TODO: 4/21/19 implement all of functions :)))

    @Override
    public void loadGameInfo() {
        view.showGameInfo(GameContents.getCurrentGame());
    }

    @Override
    public void loadMinions(boolean myMinions) {
        Player player;
        if (myMinions) {
            player = GameContents.getCurrentGame().getCurrentPlayer();
        } else {
            player = GameContents.getCurrentGame().getOpponentPlayer();
        }
        view.showMinions(player.getAccount().getName(), player.getUnits());
    }

    @Override
    public void loadCardInfo(String playerName, String cardName, int gameCardID) {
        Game game = GameContents.getCurrentGame();
        Player player = game.getPlayer(playerName);
        if (player == null) {
            Notify.logError("This player isn't in the game.");
        } else {
            Unit unit = player.getUnit(cardName, gameCardID);
            if (unit == null) {
                Notify.logError("This unit isn't in the game.");
            } else {
                view.showCardInfo(unit);
            }
        }
    }

    @Override
    public void selectCard(String cardName, int gameID) {

    }

    @Override
    public void moveToCell(int x, int y) {
        Game game = GameContents.getCurrentGame();
        Player currentPlayer = game.getCurrentPlayer();
        Cell cell = game.getTable().getCell(x, y);
        try {
            if (currentPlayer.getSelectedUnit() == null) {
                throw new UnitIsNotSelectedException();
            }
            if (cell == null) {
                throw new CellIsNotInTableException();
            }
            currentPlayer.moveUnit(cell);
        } catch (UnitIsNotSelectedException E) {
            Notify.logError("Sorry! First select a unit then move it!");
        } catch (CellIsNotInTableException E) {
            Notify.logError("Cell is not in table!");
        } catch (CellIsNotFreeException E) {
            Notify.logError("Cell is not free!");
        } catch (UnitMovedThisTurnException E) {
            Notify.logError("Unit has moved in this turn!");
        } catch (UnitStunnedException E) {
            Notify.logError("Unit is stunned and can't move!");
        } catch (DistanceException E) {
            Notify.logError("Distance is to far!");
        } catch (PathIsBlockException E) {
            Notify.logError("The path is blocked!");
        }
    }

    @Override
    public void attack(String oppCardID) {

    }

    @Override
    public void attackCombo(String oppCardID, ArrayList<String> myCardIDs) {

    }

    @Override
    public void useSpecialPower(int x, int y) {

    }

    @Override
    public void loadHand() {

    }

    @Override
    public void insertCard(String cardName, int x, int y) {
        try {
            Game game = GameContents.getCurrentGame();
            Player currentPlayer = game.getCurrentPlayer();
            Card cardToInsert = currentPlayer.getHand().getCard(cardName);
            Cell cell = game.getTable().getCell(x, y);
            if (cell == null) {
                throw new CellIsNotInTableException();
            }
            if (cardToInsert == null) {
                throw new CardNotInHandException();
            } else {
                if (cardToInsert.getClass() == Hero.class || cardToInsert.getClass() == Minion.class) {
                    currentPlayer.putUnit(cell, (Unit) cardToInsert);
                } else {
                    currentPlayer.castSpellCard((SpellCard) cardToInsert, cell);
                }
            }
        } catch (CardNotInHandException E) {
            Notify.logError("This card doesn't exist in your hand.");
        } catch (CellIsNotInTableException E) {
            Notify.logError("The cell is not in the table!");
        } catch (CellIsNotFreeException E) {
            Notify.logError("Cell is not free!");
        } catch (NotEnoughManaException E) {
            Notify.logError("You don't have enough mana.");
        } catch (InvalidTargetException E) {
            Notify.logError("Invalid target for spell!");
        }
    }

    @Override
    public void endTurn() {
        try {
            GameContents.getCurrentGame().endTurn();
        } catch (GameIsEndException E) {
            Notify.logError("Sorry! Game is finished!!");
        }
    }

    @Override
    public void loadCollectables() {

    }

    @Override
    public void selectCollectable(int collectableID) {

    }

    @Override
    public void loadSelectedCollectableInfo() {

    }

    @Override
    public void useSelectedCollectable(int x, int y) {

    }

    @Override
    public void loadNextCard() {

    }

    @Override
    public void finishTheGame() {

    }
}
