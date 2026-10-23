// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellFuncSignature extends PsiElement {

  @Nullable
  ZellGenericParams getGenericParams();

  @Nullable
  ZellParamList getParamList();

  @Nullable
  ZellType getType();

  @NotNull
  PsiElement getIdentifier();

}
