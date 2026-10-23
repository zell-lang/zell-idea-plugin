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

public class ZellConstDeclImpl extends ZellDeclMixin implements ZellConstDecl {

  public ZellConstDeclImpl(ASTNode node) {
    super(node);
  }

  public ZellConstDeclImpl(ZellDeclStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitConstDecl(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZellExpr getExpr() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, ZellExpr.class));
  }

  @Override
  @Nullable
  public ZellType getType() {
    return PsiTreeUtil.getChildOfType(this, ZellType.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return notNullChild(findChildByType(IDENTIFIER));
  }

}
