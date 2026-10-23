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

public class ZellInterfaceDeclImpl extends ZellDeclMixin implements ZellInterfaceDecl {

  public ZellInterfaceDeclImpl(ASTNode node) {
    super(node);
  }

  public ZellInterfaceDeclImpl(ZellDeclStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull ZellVisitor visitor) {
    visitor.visitInterfaceDecl(this);
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
  @NotNull
  public List<ZellBlock> getBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellBlock.class);
  }

  @Override
  @NotNull
  public List<ZellEos> getEosList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellEos.class);
  }

  @Override
  @NotNull
  public List<ZellFuncSignature> getFuncSignatureList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellFuncSignature.class);
  }

  @Override
  @Nullable
  public ZellGenericParams getGenericParams() {
    return PsiTreeUtil.getChildOfType(this, ZellGenericParams.class);
  }

  @Override
  @NotNull
  public List<ZellNamedType> getNamedTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZellNamedType.class);
  }

}
