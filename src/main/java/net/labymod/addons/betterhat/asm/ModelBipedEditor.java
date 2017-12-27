package net.labymod.addons.betterhat.asm;

import org.objectweb.asm.tree.*;

public class ModelBipedEditor extends ClassEditor
{
    public ModelBipedEditor() {
        super(ClassEditor.ClassEditorType.CLASS_NODE);
    }
    
    public void accept(final String name, final ClassNode node) {
        for (final MethodNode m : node.methods) {
            if (!m.desc.endsWith(";FFFFFF)V")) {
                continue;
            }
            System.out.println("Accepted modelbiped");
            final InsnList list = new InsnList();
            list.add(new VarInsnNode(25, 0));
            list.add(new VarInsnNode(25, 0));
            list.add(new VarInsnNode(25, 1));
            list.add(new VarInsnNode(23, 6));
            list.add(new VarInsnNode(23, 7));
            list.add(new MethodInsnNode(184, "ModelBipedMethod", "onRender", "(Ljava/lang/Object;Ljava/lang/Object;FF)V", false));
            m.instructions.insertBefore(m.instructions.get(0), list);
        }
    }
}
