// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellLambdaExpr extends PsiElement {

  @Nullable
  ZellBlock getBlock();

  @Nullable
  ZellExpr getExpr();

  @Nullable
  ZellLambdaNames getLambdaNames();

  @Nullable
  ZellParamList getParamList();

  @Nullable
  ZellType getType();

}
