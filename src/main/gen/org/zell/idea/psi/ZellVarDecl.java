// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellVarDecl extends PsiElement {

  @Nullable
  ZellExpr getExpr();

  @Nullable
  ZellType getType();

  @Nullable
  ZellVarTuple getVarTuple();

  @Nullable
  PsiElement getIdentifier();

}
