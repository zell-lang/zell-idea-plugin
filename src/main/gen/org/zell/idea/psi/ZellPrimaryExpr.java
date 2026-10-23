// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellPrimaryExpr extends PsiElement {

  @NotNull
  List<ZellExpr> getExprList();

  @Nullable
  ZellLambdaExpr getLambdaExpr();

  @Nullable
  ZellLiteral getLiteral();

  @Nullable
  ZellMapLiteral getMapLiteral();

  @Nullable
  ZellPrimaryExpr getPrimaryExpr();

  @Nullable
  ZellSliceLiteral getSliceLiteral();

  @Nullable
  ZellStructLiteral getStructLiteral();

  @Nullable
  PsiElement getIdentifier();

}
