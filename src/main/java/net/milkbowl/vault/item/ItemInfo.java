/* This file is part of Vault.

    Vault is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Vault is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Vault.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.boomboompower.itempickupnotifier;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_9_R1.LocaleI18n;

@SuppressWarnings({"deprecation"})
public class ItemInfo {

    public final Material material;
    public final short subTypeId;
    public final String name;
    public final String[][] search;
    
    public ItemInfo(String[][] search, Material material) {
    	String NMSName = CraftItemStack.asNMSCopy(new ItemStack(material)).getItem().getName() + ".name";
    	String name = LocaleI18n.get(NMSName);
        this.material = material;
        this.name = name;
        this.subTypeId = 0;
        this.search = search.clone();
        Bukkit.getConsoleSender().sendMessage("Logged " + name + " with arguments " + search + " for item " + material);
    }

    public ItemInfo(String[][] search, Material material, short subTypeId) {
    	String NMSName = CraftItemStack.asNMSCopy(new ItemStack(material)).getItem().getName() + ".name";
    	String name = LocaleI18n.get(NMSName);
        this.name = name;
        this.material = material;
        this.subTypeId = subTypeId;
        this.search = search.clone();
        Bukkit.getConsoleSender().sendMessage("Logged " + name + " with arguments " + search + " for item " + material + " SubID(" + subTypeId + ").");
    }

    public Material getType() {
        return material;
    }

    public short getSubTypeId() {
        return subTypeId;
    }

    public int getStackSize() {
        return material.getMaxStackSize();
    }

    @Deprecated
    public int getId() {
        return material.getId();
    }

    public boolean isEdible() {
        return material.isEdible();
    }
    
    public boolean isBlock() {
        return material.isBlock();
    }
    
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.getId();
        hash = 17 * hash + this.subTypeId;
        return hash;
    }

    public boolean isDurable() {
        return (material.getMaxDurability() > 0);
    }

    public ItemStack toStack() {
        return new ItemStack(this.material, 1, subTypeId);
    }
    
    @Override
    public String toString() {
        return String.format("%s[%d:%d]", name, material.getId(), subTypeId);
    }	
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!(obj instanceof ItemInfo)) {
            return false;
        } else {
            return ((ItemInfo) obj).material == this.material && ((ItemInfo) obj).subTypeId == this.subTypeId;
        }
    }
}
