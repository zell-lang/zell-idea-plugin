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

public class ZellPostfixImpl extends ASTWrapperPsiElement implements ZellPostfix {

  public ZellPostfixImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitPostfix(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZellArgList getArgList() {
    return PsiTreeUtil.getChildOfType(this, ZellArgList.class);
  }

  @Override
  @Nullable
  public ZellExpr getExpr() {
    return PsiTreeUtil.getChildOfType(this, ZellExpr.class);
  }

  @Override
  @Nullable
  public ZellGenericArgs getGenericArgs() {
    return PsiTreeUtil.getChildOfType(this, ZellGenericArgs.class);
  }

  @Override
  @Nullable
  public ZellNameSegment getNameSegment() {
    return PsiTreeUtil.getChildOfType(this, ZellNameSegment.class);
  }

  @Override
  @Nullable
  public ZellRangeExpr getRangeExpr() {
    return PsiTreeUtil.getChildOfType(this, ZellRangeExpr.class);
  }

}
