// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellForIn extends PsiElement {

  @Nullable
  ZellExpr getExpr();

  @Nullable
  ZellRangeExpr getRangeExpr();

  @NotNull
  List<ZellVarName> getVarNameList();

  @NotNull
  PsiElement getIdentifier();

}
