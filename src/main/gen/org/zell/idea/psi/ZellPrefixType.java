// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellPrefixType extends PsiElement {

  @Nullable
  ZellArrayType getArrayType();

  @Nullable
  ZellFuncType getFuncType();

  @Nullable
  ZellMapType getMapType();

  @Nullable
  ZellNamedType getNamedType();

  @Nullable
  ZellPointerType getPointerType();

  @Nullable
  ZellSliceType getSliceType();

  @Nullable
  ZellTupleType getTupleType();

}
