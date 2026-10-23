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

public class ZellTopLevelDeclImpl extends ASTWrapperPsiElement implements ZellTopLevelDecl {

  public ZellTopLevelDeclImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitTopLevelDecl(this);
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
  public ZellConstDecl getConstDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellConstDecl.class);
  }

  @Override
  @Nullable
  public ZellEnumDecl getEnumDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellEnumDecl.class);
  }

  @Override
  @Nullable
  public ZellEos getEos() {
    return PsiTreeUtil.getChildOfType(this, ZellEos.class);
  }

  @Override
  @Nullable
  public ZellExternFuncDecl getExternFuncDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellExternFuncDecl.class);
  }

  @Override
  @Nullable
  public ZellFuncDecl getFuncDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellFuncDecl.class);
  }

  @Override
  @Nullable
  public ZellInterfaceDecl getInterfaceDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellInterfaceDecl.class);
  }

  @Override
  @Nullable
  public ZellStructDecl getStructDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellStructDecl.class);
  }

  @Override
  @Nullable
  public ZellTypeAliasDecl getTypeAliasDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellTypeAliasDecl.class);
  }

  @Override
  @Nullable
  public ZellVarDecl getVarDecl() {
    return PsiTreeUtil.getChildOfType(this, ZellVarDecl.class);
  }

}
