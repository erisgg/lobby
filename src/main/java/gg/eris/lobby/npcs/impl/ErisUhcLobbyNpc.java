package gg.eris.lobby.npcs.impl;

import gg.eris.commons.core.util.Pair;
import gg.eris.commons.core.util.RandomUtil;
import gg.eris.lobby.ErisLobby;
import gg.eris.lobby.npcs.ErisBaseLobbyNpc;
import java.util.ArrayList;
import java.util.List;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class ErisUhcLobbyNpc extends ErisBaseLobbyNpc {

  // Skins in Pairs<Value, Signature>
  private static final List<Pair<String, String>> TEXTURES = List.of(
      Pair.of(
          "ewogICJ0aW1lc3RhbXAiIDogMTU5NDkyODUxNjYzOSwKICAicHJvZmlsZUlkIiA6ICIwZjczMDA3NjEyNGU0NGM3YWYxMTE1NDY5YzQ5OTY3OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJPcmVfTWluZXIxMjMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzNiMjZjMzYxOTNjZjM5Yzg4YTgyMjcyNjY2MjdiZTY2MmFhNDcxZDcxYzg2NWJmYTY0NGMwOGQyYTVlMzc1IgogICAgfQogIH0KfQ==",
          "GLvN4gTx"
              + "+AzH6OupqIwpWRMk4MvY2AiUF32A0vMGlklBAXPyfeZMnDct9G8SQtNiyaCYEuDvn1H5781F8DMAcE1D42YZyEAFv6MYY74KpGOU9xEWFacdUR3JZPPHfrVJiyMN8+W38wz2ntYmRdMDx3vH4Piar0Vx8PC5AVWkiSSNAvUWL6q22k6Zc2Tiw6yDkWlXaGqejuIgdYgxrW/uPTlt57VL/Unoll/Z33Ia8lLg6PJdSRIkOqPF8WDOYMwQk403or7uZ7ncCQDWxoUf5d2ycsuEEaH2s8SNwwZn++iCVNJMwJxuEK4ssPMajH2pNczuRUdysCZ1S1TGfBIhdxL0oRcwlRQwuLb1g5kJDAfpO8YhiAw83c+VFzMDbhnm2eZspsE7POkj+FBnN6s0kNJ2lmn9OCbi3x7HesEEQG/G7uKmluPiE7gyif8i05L2j1bdTARyumj8fLgeIpagdbwpUrmFGvkdIdxzNRs6p+NBeMoCjMnUC66a4hwoPG8eGeEEfg21NZYpa08r1dvst39oAru29Mdewj6KyHhYgv3pR1DgsvgeFgXaQDkYNvEp0TADGBeJWxlg0hKtQ5jtShKpRCqiwUSrQ+oNhBOcGGwj/n2yv9dF0tmh9QF/p5/rzbGTwMzcD+06YdP0R7nb5VyFSPCScq1fRx/8LjcuhSQ="
      ), // Wisp
      Pair.of(
          "eyJ0aW1lc3RhbXAiOjE1NTM5NDg3MTc4MDEsInByb2ZpbGVJZCI6IjUzYmQ4MDAwMmY2YjQyYTk5NTQzYjkzOTc5NDAxN2FhIiwicHJvZmlsZU5hbWUiOiJSb2xscyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhN2MxNWQyMjUzYTU1Y2QxMmJhNTdkYmU5NzdkN2M4NjIwMWIwYWNlNTMyMjBjMjc5NzdkYzJkOTg0ZDQ4YSJ9fX0=",
          "VGNSfNBbFlS+MqkIppcrpCgTS8TJ6QvV4cAvQ++KIV1hlqMQD"
              + "+xTvJBAFiy8HcNZAdNu6WR8Kw9XHPrFaALEDjS72HHhC0julQNHttPg51tVD9cdvy7Gfa4/nMwZi3OE5uKeKxyo4jfUAILFpkDLeXayFrvvAvo7bu47zU/VmqqD8EoS8xpqDoe5ntRS6wPD/xmlw5TsOpUeAI6ngYNyGcMRC9IMk1GGNR+ffPD1ZZ5T3XH1AHFRByJngS59RkC7hCwLeFFJc5OoipuMXAoj9DdX0UmKRN8nCySZRXjFvMLt2hwlUjX9iF4mB9fpnmgqzx6uE84DHmlAV4+NVuTnTG8vmvzwvGKWgBdtXMAlR8SJ0tBqMw9MXiL7rzVUBAIRhjW1pXw9294H7TWdG2318T4nKhmAp/T59l2HB4g46lYjjbkVFOlMspdJILqB+QBzpnlw1RLiA+4aPVVi5cmGvrTLe+R4bi+Tw/SNa3nN1ZQkS0MqV1L6vfSJmLOPbpZHMdLY1oQW43TEmjEBe1cIh46E8hOOScUific8n6xiQBTqvKgmYBgBP0IAP42n58SNaqvIUrZNaCrTH3uUn3Lf5T/bJAM+PFG1dwUdKOvRnmTOiCTBL8PRIxvm2UY7Eg6M51TyVCkRY8Gz2Qf7ke1SIHVtS6CKbc1EF10wlcvZdoM="
      ), // Chimah
      Pair.of(
          "ewogICJ0aW1lc3RhbXAiIDogMTYyODQ1MTczODkzMiwKICAicHJvZmlsZUlkIiA6ICJiNTM5NTkyMjMwY2I0MmE0OWY5YTRlYmYxNmRlOTYwYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYXJpYW5hZmFnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VmZWE1YjA2OWZmN2YzMTI4MjVlNjBkNjQ3MGY0NzIzM2FlN2FhM2U2ZGU1ZmI5ODlkMTdmODRhNjg5YTMyNTIiCiAgICB9CiAgfQp9",
          "w3zv3AvdEDIghXSa"
              + "+OSBFApJYGmjf5srheu2OkOruMslZMpdl3R1yUxsoU7obAqyipzBHa3X8mTxJW4k8AyBFTWI757hdtlq0VDU8nV0Yw2+8hV27pZL85/uxo6mH1PDbncpfekjz7Bh6zeJJmEsAIyl82bU40cnzifBs1MVn29WVcaI9l44eOdOU86wdKPw+v0oGjmEcbE/ieXu5H2MnpC6pkzckDKVYI6/aHf2Vs4D+KSFVOoNKLyAvwwP0hYsRXfoh8tnn8lSwidPhxJcbkElF82d4l0PmGMPGehmFeYfp7DnlTaSlcGfkoi6hW7goBNSBQo3+L3Ht9VY4lK0qmjcZ91qNHDGX19Udt/Krsi5MpncNTqLYiwR4E/Rf916f02lfoWo+4v9pcFtiHtduysIawFz+pieyrU877gq+Cgba4UX/J//otB4SdRI/wH7P0UN7CtndyuyFNlle44pcFb0pGtpPJTQendvBWDlibyM/Q/737G2hZrqkSSnI8q/QuDyI9FYTYaghrHfY9mNcEq0v2akjR2zw1Zmo4a8Y8Qu5P7KWSBfeBUtwNDlzgnACYjYenF8/t+FqxF34m8XMq/bmLgszUsxV2Fj5bXEO/dgjrurCHy8kGjExoVrXruPXZE+D6RESSjOv9VDnccyaxQE6oDUtmuNL+XL5QXY8KA="
      ), // Sweatgod
      Pair.of(
          "ewogICJ0aW1lc3RhbXAiIDogMTYyNjMyODg0MDYyOCwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MTQxNDE0MWgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ1YmYzMzY5OThiMzhmM2Y3MTIzNzU1YjI0ZTljNWQwMjAwMTUzYWViYWM2YTU0OTBlNzhkZjdkMDJhZmJiOCIKICAgIH0KICB9Cn0=",
          "ueqZ3TuWnotaJSCwZCnlDcmiy2GfNCBenqmw63SxnlipdSKCn2u4fE0GWm7nQGXxUyxxoxq7pDVa9x6Bun88YwWIrjA5P5FPyxG2l1ZkQGz9SRG0basALGKo+10AHhtgnHyuIrRS6DFWd8l3Ry0zph0qtwAAcTXgHF4ScrttL6bVcAQ80PMVfPdSZ02hjy2aF5EW7B5QDtbMDW7uzYp+3HfTe10imFXbkmghCIO7Gj0/ZXJ1dsUro3lWU4DRWA1HjSxGb10qTxzgTX5tYbujATK49Ekb4yodcIzkUNZZP8X/2r/vTsd73FSOKj4RMRNvS0DP2RkaxF7lkATozB1xgvw7Tm19LpEwTi3bqh6fjJ51S2n6kTWTv4BhVHpaAj/r7dNWTeZR5Kh6TssHRhTNUEOh45c2Otk2gypOyLy01oX4XUSMPtY03Gi0SQYFOinDgzUv3JaNKstwCSwa8qkLkBvGskXJrtU3gwPq+O8biS0/HWfWqV1wlvJKZSkhKMt3V3KIfXf90Vl2hSEJx6H8Z6/e4t2dcJpmKPisL28qcVhw3vIP8d9V0wh4n/L5X/Q8WJP8azB5k52AVG6NEdJ1+KdQdBmhhvHLeLu1+mSKafJOu1WsjcZ9hol/1QLlBx4FQrxgy6JBzX1I+GQx6+frn8oN93muDfkZSHAhYzXRjws="
      ) // Suep
  );

  private Pair<String, String> skin;

  public ErisUhcLobbyNpc(ErisLobby plugin) {
    Bukkit.getScheduler().runTaskTimer(plugin, () -> {
      if (this.skin == null) {
        this.skin = RandomUtil.selectRandom(TEXTURES);
        this.citizensNpc.getOrAddTrait(SkinTrait.class).setTexture(this.skin.getKey(),
            this.skin.getValue());
      } else {
        List<Pair<String, String>> textures = new ArrayList<>(TEXTURES);
        textures.remove(this.skin);
        this.skin = RandomUtil.selectRandom(textures);
      }
    }, 0, 5 * 60 * 20);
  }

  @Override
  public String getTextureSignature() {
    return TEXTURES.get(0).getValue();
  }

  @Override
  public String getTextureData() {
    return TEXTURES.get(0).getKey();
  }

  @Override
  public String getIgn() {
    return "Eris UHC";
  }

  @Override
  public String getId() {
    return "uhc-npc";
  }

  @Override
  public void onClickedByPlayer(Player player) {
    player.performCommand("uhc");
  }

}
