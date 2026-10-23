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

public class ZellPrefixTypeImpl extends ASTWrapperPsiElement implements ZellPrefixType {

  public ZellPrefixTypeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitPrefixType(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZellArrayType getArrayType() {
    return PsiTreeUtil.getChildOfType(this, ZellArrayType.class);
  }

  @Override
  @Nullable
  public ZellFuncType getFuncType() {
    return PsiTreeUtil.getChildOfType(this, ZellFuncType.class);
  }

  @Override
  @Nullable
  public ZellMapType getMapType() {
    return PsiTreeUtil.getChildOfType(this, ZellMapType.class);
  }

  @Override
  @Nullable
  public ZellNamedType getNamedType() {
    return PsiTreeUtil.getChildOfType(this, ZellNamedType.class);
  }

  @Override
  @Nullable
  public ZellPointerType getPointerType() {
    return PsiTreeUtil.getChildOfType(this, ZellPointerType.class);
  }

  @Override
  @Nullable
  public ZellSliceType getSliceType() {
    return PsiTreeUtil.getChildOfType(this, ZellSliceType.class);
  }

  @Override
  @Nullable
  public ZellTupleType getTupleType() {
    return PsiTreeUtil.getChildOfType(this, ZellTupleType.class);
  }

}
