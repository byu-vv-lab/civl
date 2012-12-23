package edu.udel.cis.vsl.civl.ast.entity.IF;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.LinkageKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

public interface EntityFactory {

	Scope newScope(ScopeKind kind, Scope parent, ASTNode root);

	Variable newVariable(String name, LinkageKind linkage, Type type);

	Function newFunction(String name, LinkageKind linkage, Type type);

	StructureOrUnion newStructureOrUnion(StructureOrUnionType type);

	Enumeration newEnumeration(EnumerationType type);

	Enumerator newEnumerator(EnumeratorDeclarationNode declaration,
			EnumerationType type, Value value);

	Field newField(FieldDeclarationNode declaration, ObjectType type,
			Value bitWidth, StructureOrUnion structureOrUnion);

	Typedef newTypedef(String name, Type type);

	Label newLabel(OrdinaryLabelNode declaration);

}
