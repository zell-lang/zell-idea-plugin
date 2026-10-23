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

public class ZellIfStmtImpl extends ASTWrapperPsiElement implements ZellIfStmt {

  public ZellIfStmtImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitIfStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZellBlock> getBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellBlock.class);
  }

  @Override
  @NotNull
  public ZellExpr getExpr() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, ZellExpr.class));
  }

  @Override
  @Nullable
  public ZellIfStmt getIfStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellIfStmt.class);
  }

}
