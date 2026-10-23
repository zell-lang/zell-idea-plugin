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

public class ZellStatementImpl extends ASTWrapperPsiElement implements ZellStatement {

  public ZellStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZellAssignStmt getAssignStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellAssignStmt.class);
  }

  @Override
  @Nullable
  public ZellBlock getBlock() {
    return PsiTreeUtil.getChildOfType(this, ZellBlock.class);
  }

  @Override
  @Nullable
  public ZellBreakStmt getBreakStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellBreakStmt.class);
  }

  @Override
  @Nullable
  public ZellConstDecl getConstDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellConstDecl.class);
  }

  @Override
  @Nullable
  public ZellContinueStmt getContinueStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellContinueStmt.class);
  }

  @Override
  @Nullable
  public ZellDeferStmt getDeferStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellDeferStmt.class);
  }

  @Override
  @Nullable
  public ZellEos getEos() {
    return PsiTreeUtil.getChildOfType(this, ZellEos.class);
  }

  @Override
  @Nullable
  public ZellExprStmt getExprStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellExprStmt.class);
  }

  @Override
  @Nullable
  public ZellForStmt getForStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellForStmt.class);
  }

  @Override
  @Nullable
  public ZellIfStmt getIfStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellIfStmt.class);
  }

  @Override
  @Nullable
  public ZellReturnStmt getReturnStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellReturnStmt.class);
  }

  @Override
  @Nullable
  public ZellSwitchStmt getSwitchStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellSwitchStmt.class);
  }

  @Override
  @Nullable
  public ZellTryStmt getTryStmt() {
    return PsiTreeUtil.getChildOfType(this, ZellTryStmt.class);
  }

  @Override
  @Nullable
  public ZellVarDecl getVarDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellVarDecl.class);
  }

}
