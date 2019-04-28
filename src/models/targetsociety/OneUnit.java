package models.targetsociety;

import models.Buff;
import models.Cell;
import models.Player;

import java.util.ArrayList;

public class OneUnit extends UnitTargetSociety {
    public OneUnit(TargetType targetType, TargetTeam targetTeam) {
        super(targetType, targetTeam);
    }

    @Override
    public boolean canCast(Player player, Cell cell) {
        return cell.hasUnit() && doesEffect(cell.getUnit(), player);
    }

    @Override
    public void cast(Player player, Cell cell, ArrayList<Buff> buffs) {
        if (canCast(player, cell))
            cell.getUnit().addBuffs(buffs);
    }
}
