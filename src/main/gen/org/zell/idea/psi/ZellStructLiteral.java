// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellStructLiteral extends PsiElement {

  @NotNull
  List<ZellFieldInit> getFieldInitList();

  @Nullable
  ZellGenericArgs getGenericArgs();

  @NotNull
  ZellQualifiedName getQualifiedName();

}
