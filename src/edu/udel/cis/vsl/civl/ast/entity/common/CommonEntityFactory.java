package edu.udel.cis.vsl.civl.ast.entity.common;

import edu.udel.cis.vsl.civl.ast.entity.IF.Entity.LinkageKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.EntityFactory;
import edu.udel.cis.vsl.civl.ast.entity.IF.Enumeration;
import edu.udel.cis.vsl.civl.ast.entity.IF.Enumerator;
import edu.udel.cis.vsl.civl.ast.entity.IF.Field;
import edu.udel.cis.vsl.civl.ast.entity.IF.Function;
import edu.udel.cis.vsl.civl.ast.entity.IF.Label;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope;
import edu.udel.cis.vsl.civl.ast.entity.IF.Scope.ScopeKind;
import edu.udel.cis.vsl.civl.ast.entity.IF.StructureOrUnion;
import edu.udel.cis.vsl.civl.ast.entity.IF.Typedef;
import edu.udel.cis.vsl.civl.ast.entity.IF.Variable;
import edu.udel.cis.vsl.civl.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.EnumeratorDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.declaration.FieldDeclarationNode;
import edu.udel.cis.vsl.civl.ast.node.IF.label.OrdinaryLabelNode;
import edu.udel.cis.vsl.civl.ast.type.IF.EnumerationType;
import edu.udel.cis.vsl.civl.ast.type.IF.ObjectType;
import edu.udel.cis.vsl.civl.ast.type.IF.StructureOrUnionType;
import edu.udel.cis.vsl.civl.ast.type.IF.Type;
import edu.udel.cis.vsl.civl.ast.value.IF.Value;

public class CommonEntityFactory implements EntityFactory {

	@Override
	public Scope newScope(ScopeKind kind, Scope parent, ASTNode root) {
		return new CommonScope(kind, (CommonScope) parent, root);
	}

	@Override
	public Variable newVariable(String name, LinkageKind linkage, Type type) {
		return new CommonVariable(name, linkage, type);
	}

	@Override
	public Function newFunction(String name, LinkageKind linkage, Type type) {
		return new CommonFunction(name, linkage, type);
	}

	@Override
	public StructureOrUnion newStructureOrUnion(StructureOrUnionType type) {
		return new CommonStructureOrUnion(type);
	}

	@Override
	public Enumeration newEnumeration(EnumerationType type) {
		return new CommonEnumeration(type);
	}

	@Override
	public Enumerator newEnumerator(EnumeratorDeclarationNode declaration,
			EnumerationType type, Value value) {
		return new CommonEnumerator(declaration, type, value);
	}

	@Override
	public Field newField(FieldDeclarationNode declaration, ObjectType type,
			Value bitWidth, StructureOrUnion structureOrUnion) {
		return new CommonField(declaration, type, bitWidth, structureOrUnion);
	}

	@Override
	public Typedef newTypedef(String name, Type type) {
		return new CommonTypedef(name, type);
	}

	@Override
	public Label newLabel(OrdinaryLabelNode declaration) {
		return new CommonLabel(declaration);
	}

}
