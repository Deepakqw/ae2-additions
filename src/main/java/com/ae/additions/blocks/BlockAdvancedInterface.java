package com.ae.additions.blocks;

import appeng.block.misc.BlockInterface;
import appeng.client.render.blocks.RenderBlockInterface;
import appeng.core.features.AEFeature;
import appeng.util.Platform;
import com.ae.additions.AE2Addition;
import com.ae.additions.client.render.RenderAdvancedBlockInterface;
import com.ae.additions.proxy.CommonProxy;
import com.ae.additions.tile.TileAdvancedInterface;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.EnumSet;

public class BlockAdvancedInterface extends BlockInterface {

    protected IIcon[] icons = new IIcon[3];

    public BlockAdvancedInterface() {
        super();
        this.setTileEntity(TileAdvancedInterface.class);
        this.setFeature(EnumSet.of(AEFeature.Core));
        setBlockName("advanced_interface");
    }

    @SideOnly(Side.CLIENT)
    protected String getTextureName() {
        return AE2Addition.MODID + ":" + this.getUnlocalizedName().replace("tile.", "");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected RenderBlockInterface getRenderer() {
        return new RenderAdvancedBlockInterface();
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        this.registerBlockIconsSpecial(register);
    }

    public void registerBlockIconsSpecial(IIconRegister register) {
        this.icons[0] = register.registerIcon(AE2Addition.MODID + ":advanced_interface");
        this.icons[1] = register.registerIcon(AE2Addition.MODID + ":advanced_interface_alternate");
        this.icons[2] = register.registerIcon(AE2Addition.MODID + ":advanced_interface_alternate_arrow");
    }

    public IIcon getIcon(int index) {
        return this.icons[MathHelper.clamp_int(index, 0, 2)];
    }

    @Override
    public boolean onActivated(World w, int x, int y, int z, EntityPlayer p, int side, float hitX, float hitY, float hitZ) {
        if (p.isSneaking()) {
            return false;
        }
        TileAdvancedInterface tg = this.getTileEntity(w, x, y, z);
        if (tg != null) {
            if (Platform.isServer()) {
                Platform.openGUI(p, tg, ForgeDirection.getOrientation(side), CommonProxy.getGuiAdvInterface());
            }
            return true;
        }
        return false;
    }

}