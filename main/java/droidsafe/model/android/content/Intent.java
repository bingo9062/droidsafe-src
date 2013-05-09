package droidsafe.model.android.content;

import droidsafe.model.ModeledClass;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.jimple.spark.pag.AllocNode;

public class Intent extends ModeledClass {

  private final static Logger logger = LoggerFactory.getLogger(Intent.class);

  droidsafe.model.java.lang.String mAction;
  droidsafe.model.java.lang.String mType;
  droidsafe.model.java.lang.String mPackage;
  droidsafe.model.android.net.Uri mData;
  
  public Intent(AllocNode allocNode) {
    super(allocNode);
    this.mAction = new droidsafe.model.java.lang.String();
    this.mType = new droidsafe.model.java.lang.String();
    this.mPackage = new droidsafe.model.java.lang.String();
  }
  
  public void _init_(){
  }

  public void _init_(droidsafe.model.java.lang.String action){
    this.setAction(action);
  } 
  
  public void _init_(droidsafe.model.android.test.mock.MockContext context, droidsafe.model.java.lang.String cls) {
    // TODO: check the android source code for this constructor
    this.setAction(cls);
  }
  
  public void _init_(droidsafe.model.android.content.Context context, java.lang.Class cls) {
  }

  public void setAction(droidsafe.model.java.lang.String mAction){
    this.mAction.incorporateString(mAction);
  }

  public void setData(droidsafe.model.android.net.Uri mUri) {
    this.mData = mUri;
  }

  public void setType(droidsafe.model.java.lang.String mType){
    this.mType.incorporateString(mType);
  }
  
  public void setPackage(droidsafe.model.java.lang.String mPackage){
    this.mPackage.incorporateString(mPackage);
  }

  public droidsafe.model.java.lang.String getAction() {
    return this.mAction;
  }

  public droidsafe.model.java.lang.String getPackage() {
    return this.mPackage;
  }

  public droidsafe.model.java.lang.String getType() {
    return this.mType;
  }

  public droidsafe.model.android.net.Uri getData() {
    return this.mData;
  }

  public void getStringExtra(droidsafe.model.java.lang.String name) {

  }


  @Override
  public String toString(){
    String str = "<modeled Intent" + this.getId() + "> {";
    if (this.invalidated) {
      str += "invalidated";
    } else {
      ArrayList<String> attrs = new ArrayList();
      if(this.mAction.getPossibleValues().size() > 0)
        attrs.add("action: " + this.mAction);
      if(this.mType.getPossibleValues().size() > 0)
        attrs.add("type: " + this.mType);
      if(this.mData != null)
        attrs.add("data: " + this.mData);
      if(this.mPackage.getPossibleValues().size() > 0)
        attrs.add("package: " + this.mPackage);
      str = str + StringUtils.join(attrs.toArray(), ", ");
    }
    return str + "}";
  }
}
