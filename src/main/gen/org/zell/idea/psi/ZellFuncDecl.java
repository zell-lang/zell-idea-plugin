// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import org.zell.idea.stubs.ZellDeclStub;

public interface ZellFuncDecl extends ZellNamedElement, StubBasedPsiElement<ZellDeclStub> {

  @NotNull
  ZellBlock getBlock();

  @NotNull
  ZellFuncSignature getFuncSignature();

}
