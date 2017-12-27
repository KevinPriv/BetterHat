package net.labymod.addons.betterhat.asm;

import org.objectweb.asm.tree.*;
import org.objectweb.asm.*;

public abstract class ClassEditor extends ClassVisitor
{
    private ClassEditorType type;

    public ClassEditor(final ClassEditorType type) {
        super(262144);
        this.type = type;
    }

    public void accept(final String name, final ClassNode node) {
    }

    public void accept(final String name, final ClassVisitor visitor) {
        this.cv = visitor;
    }

    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    public String visitMapping(final String typeName) {
        return typeName;
    }

    public ClassEditorType getType() {
        return this.type;
    }

    public enum ClassEditorType
    {
        CLASS_VISITOR,
        CLASS_NODE,
        CLASS_VISITOR_AND_REMAPPER;
    }
}
