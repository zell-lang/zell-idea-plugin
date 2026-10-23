// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellPostfix extends PsiElement {

  @Nullable
  ZellArgList getArgList();

  @Nullable
  ZellExpr getExpr();

  @Nullable
  ZellGenericArgs getGenericArgs();

  @Nullable
  ZellNameSegment getNameSegment();

  @Nullable
  ZellRangeExpr getRangeExpr();

}
