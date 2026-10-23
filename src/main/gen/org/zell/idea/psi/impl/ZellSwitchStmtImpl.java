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

public class ZellSwitchStmtImpl extends ASTWrapperPsiElement implements ZellSwitchStmt {

  public ZellSwitchStmtImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitSwitchStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZellDefaultCase getDefaultCase() {
    return PsiTreeUtil.getChildOfType(this, ZellDefaultCase.class);
  }

  @Override
  @Nullable
  public ZellExpr getExpr() {
    return PsiTreeUtil.getChildOfType(this, ZellExpr.class);
  }

  @Override
  @NotNull
  public List<ZellSwitchCase> getSwitchCaseList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellSwitchCase.class);
  }

}
