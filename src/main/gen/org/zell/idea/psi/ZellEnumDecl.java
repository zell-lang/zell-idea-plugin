// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import org.zell.idea.stubs.ZellDeclStub;

public interface ZellEnumDecl extends ZellNamedElement, StubBasedPsiElement<ZellDeclStub> {

  @NotNull
  List<ZellAttribute> getAttributeList();

  @NotNull
  List<ZellEnumVariant> getEnumVariantList();

  @NotNull
  List<ZellEos> getEosList();

  @Nullable
  ZellGenericParams getGenericParams();

  @Nullable
  ZellImplementsClause getImplementsClause();

  @NotNull
  List<ZellMethodDecl> getMethodDeclList();

  @NotNull
  PsiElement getIdentifier();

}
