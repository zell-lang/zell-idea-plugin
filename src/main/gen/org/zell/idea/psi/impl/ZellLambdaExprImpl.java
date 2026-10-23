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

public class ZellLambdaExprImpl extends ASTWrapperPsiElement implements ZellLambdaExpr {

  public ZellLambdaExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitLambdaExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZellBlock getBlock() {
    return PsiTreeUtil.getChildOfType(this, ZellBlock.class);
  }

  @Override
  @Nullable
  public ZellExpr getExpr() {
    return PsiTreeUtil.getChildOfType(this, ZellExpr.class);
  }

  @Override
  @Nullable
  public ZellLambdaNames getLambdaNames() {
    return PsiTreeUtil.getChildOfType(this, ZellLambdaNames.class);
  }

  @Override
  @Nullable
  public ZellParamList getParamList() {
    return PsiTreeUtil.getChildOfType(this, ZellParamList.class);
  }

  @Override
  @Nullable
  public ZellType getType() {
    return PsiTreeUtil.getChildOfType(this, ZellType.class);
  }

}
