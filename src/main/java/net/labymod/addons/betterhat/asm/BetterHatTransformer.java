package net.labymod.addons.betterhat.asm;

import net.minecraft.launchwrapper.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.*;

public class BetterHatTransformer implements IClassTransformer
{

    public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
        if (!name.equals("net.minecraft.client.model.ModelBiped") && !name.equals("bbj")) {
                return basicClass;
        }
        System.out.println("Modifying ModelBiped...");
        final ClassEditor editor = new ModelBipedEditor();
        final ClassNode node = new ClassNode();
        final ClassReader reader = new ClassReader(basicClass);
        reader.accept(node, 0);
        editor.accept(name, node);
        final ClassWriter writer = new ClassWriter(3);
        node.accept(writer);
        final byte[] retBytes = writer.toByteArray();
        return retBytes;
    }

}
