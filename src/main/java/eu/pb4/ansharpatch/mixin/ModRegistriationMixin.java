package eu.pb4.ansharpatch.mixin;

import com.lgmrszd.anshar.ModRegistration;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModRegistration.class)
public class ModRegistriationMixin {
    @Redirect(method = "registerAll", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"))
    private static Object dontRegister(Registry<?> registry, Identifier id, Object entry) {
        return entry;
    }

    @Redirect(method = "registerAll", at = @At(value = "INVOKE", target = "Lnet/fabricmc/fabric/api/networking/v1/ServerPlayNetworking;registerGlobalReceiver(Lnet/minecraft/network/packet/CustomPayload$Id;Lnet/fabricmc/fabric/api/networking/v1/ServerPlayNetworking$PlayPayloadHandler;)Z"))
    private static boolean networkingBad(CustomPayload.Id<?> type, ServerPlayNetworking.PlayPayloadHandler<?> handler) {
        return false;
    }
}
