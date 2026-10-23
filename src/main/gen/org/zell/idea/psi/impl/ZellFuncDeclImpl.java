// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.zell.idea.psi.ZellTypes.*;
import org.zell.idea.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.zell.idea.stubs.ZellDeclStub;

public class ZellFuncDeclImpl extends ZellDeclMixin implements ZellFuncDecl {

  public ZellFuncDeclImpl(ASTNode node) {
    super(node);
  }

  public ZellFuncDeclImpl(ZellDeclStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitFuncDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZellBlock getBlock() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, ZellBlock.class));
  }

  @Override
  @NotNull
  public ZellFuncSignature getFuncSignature() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, ZellFuncSignature.class));
  }

}
