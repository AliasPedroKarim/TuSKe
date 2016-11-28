package me.tuke.sktuke.expressions.customenchantments;

import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.tuke.sktuke.customenchantment.CEnchant;
import me.tuke.sktuke.customenchantment.EnchantConfig;

public class ExprEnabled extends SimplePropertyExpression<CEnchant, Boolean>{

	@Override
	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	@Nullable
	public Boolean convert(CEnchant ce) {
		return ce.getEnchant().isEnabledOnTable();
	}

	@Override
	protected String getPropertyName() {
		return "enabled";
	}
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		CEnchant ce = getExpr().getSingle(e);
		if (ce != null && delta != null){
			ce.getEnchant().setEnabledOnTable((Boolean)delta[0]);
			EnchantConfig.y.set("Enchantments." + ce.getEnchant().getId() + ".Enabled", ((Boolean)delta[0]));
			EnchantConfig.save(); 
		}
	}
	@SuppressWarnings("unchecked")
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(Boolean.class);
		return null;
		
	}

}
