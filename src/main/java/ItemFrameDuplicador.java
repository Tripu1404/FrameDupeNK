package com.tripu1404.itemframeduplicador;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.ItemFrameDropItemEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.File;
import java.util.Random;

public class ItemFrameDuplicador extends PluginBase implements Listener {

    private double duplicacionProbabilidad;
    private final Random random = new Random();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Config config = new Config(new File(this.getDataFolder(), "config.yml"), Config.YAML);
        duplicacionProbabilidad = config.getDouble("probabilidad_duplicacion", 0.25);
        Server.getInstance().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void alRemoverObjeto(ItemFrameDropItemEvent evento) {
        Item itemQuitado = evento.getItem();
        Player jugador = evento.getPlayer();

        if (random.nextDouble() < duplicacionProbabilidad) {
            jugador.getLevel().dropItem(jugador.getLocation(), itemQuitado.clone());
        }
    }
}
