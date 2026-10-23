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

public class ZellStructMemberImpl extends ASTWrapperPsiElement implements ZellStructMember {

  public ZellStructMemberImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitStructMember(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZellVisitor) accept((ZellVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZellAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellAttribute.class);
  }

  @Override
  @Nullable
  public ZellEmbedDecl getEmbedDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellEmbedDecl.class);
  }

  @Override
  @Nullable
  public ZellEos getEos() {
    return PsiTreeUtil.getChildOfType(this, ZellEos.class);
  }

  @Override
  @Nullable
  public ZellFieldDecl getFieldDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellFieldDecl.class);
  }

  @Override
  @Nullable
  public ZellMethodDecl getMethodDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellMethodDecl.class);
  }

}
