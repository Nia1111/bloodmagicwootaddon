package wootrevived.bloodmagicwootaddon;

import wootrevived.api.IWootPlugin;
import wootrevived.api.WootPlugin;
import wootrevived.api.registrations.WootUpgradeItemRegistration;
import wootrevived.bloodmagicwootaddon.upgrades.BloodCollector;

@WootPlugin
public class BloodMagicWootPlugin implements IWootPlugin {
    @Override
    public void registerUpgradeItems(WootUpgradeItemRegistration registration){
        BloodCollector.register(registration);
    }
}
