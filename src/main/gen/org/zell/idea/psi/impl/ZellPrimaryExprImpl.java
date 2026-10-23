// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.zell.idea.psi.ZellTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.zell.idea.psi.*;

public class ZellPrimaryExprImpl extends ASTWrapperPsiElement implements ZellPrimaryExpr {

  public ZellPrimaryExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitPrimaryExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZellExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellExpr.class);
  }

  @Override
  @Nullable
  public ZellLambdaExpr getLambdaExpr() {
    return PsiTreeUtil.getChildOfType(this, ZellLambdaExpr.class);
  }

  @Override
  @Nullable
  public ZellLiteral getLiteral() {
    return PsiTreeUtil.getChildOfType(this, ZellLiteral.class);
  }

  @Override
  @Nullable
  public ZellMapLiteral getMapLiteral() {
    return PsiTreeUtil.getChildOfType(this, ZellMapLiteral.class);
  }

  @Override
  @Nullable
  public ZellPrimaryExpr getPrimaryExpr() {
    return PsiTreeUtil.getChildOfType(this, ZellPrimaryExpr.class);
  }

  @Override
  @Nullable
  public ZellSliceLiteral getSliceLiteral() {
    return PsiTreeUtil.getChildOfType(this, ZellSliceLiteral.class);
  }

  @Override
  @Nullable
  public ZellStructLiteral getStructLiteral() {
    return PsiTreeUtil.getChildOfType(this, ZellStructLiteral.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
