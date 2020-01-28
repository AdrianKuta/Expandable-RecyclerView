# Multilevel Expandable RecyclerView

[![maven](https://img.shields.io/maven-central/v/com.github.adriankuta/expandable-recyclerView?style=plastic)](https://search.maven.org/artifact/com.github.adriankuta/expandable-recyclerView)
[![License](https://img.shields.io/github/license/AdrianKuta/Expandable-RecyclerView?style=plastic)](https://github.com/AdrianKuta/Expandable-RecyclerView/blob/master/LICENSE)
[![CircleCI](https://img.shields.io/circleci/build/github/AdrianKuta/Expandable-RecyclerView/master?label=CircleCI&style=plastic&logo=circleci)](https://circleci.com/gh/AdrianKuta/Expandable-RecyclerView)

With this adapter you can add expand feature to regular RecyclerView.
All objects are store in [Tree (Data structure)](https://github.com/AdrianKuta/Tree-Data-Structure), so adapter can create multilevel expandable groups.
Under the hood, the tree is flattened to simple list, so from RecyclerView's point of view it can operate as usual.


<img src="https://github.com/AdrianKuta/Expandable-RecyclerView/blob/master/Demo.gif" width="400" />

## Download

    implementation "com.github.adriankuta:expandable-recyclerView:$latest_versions"
    
## Usage
To use expandable adapter we just have to expand our Adapter class with `ExpandableRecyclerViewAdapter<T, VH>`
Where `T` is data type on which adapter will operate, `VH` is ViewHolder type.

```kotlin
class ExpandableAdapter: ExpandableRecyclerViewAdapter<String, RecyclerView.ViewHolder>() {
    
    
    override fun getTreeNodes(): ExpandableTreeNode<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        treeNode: ExpandableTreeNode<String>,
        nestLevel: Int
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, nestLevel: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
```    

`ExpandableRecyclerViewAdapter` has build-in methods to control expandable groups:
```kotlin
fun toggleGroup(expandableTreeNode: ExpandableTreeNode<T>)

fun expand(expandableTreeNode: ExpandableTreeNode<T>)

fun collapse(expandableTreeNode: ExpandableTreeNode<T>)
```

Information if group is expanded or not is stored inside `ExpandableTreeNode` class


### Creating tree

There are different ways to create tree. The easiest way is to use extensions methods prepared specially for kotlin:

```kotlin
val tree = 
    expandableTree("World") {
        child("North America") {
            child("USA")
        }
        child("Europe") {
            child("Poland") {
                child("Warsaw")
            }
            child("Germany")
        }
        child("Asia") {
            child("China")
        }
    }
```

But in case when you want create tree at runtime, you can use `ExpandableTreeNode<T>` class to build tree:

```kotlin
val root = ExpandableTreeNode("World")
val northA = ExpandableTreeNode("North America")
val europe = ExpandableTreeNode("Europe")
val asia = ExpandableTreeNode("Asia")

root.addChild(northA)
root.addChild(europe)
root.addChild(asia)

val poland = ExpandableTreeNode("Poland")
europe.addChild(poland)
// etc.
```

## Sample

Full example of this library is available in app module.

## Contribution

Project is still during development and improvements.
Issues with BUGs or suggestions are welcome.
Also please feel free to fork library and contribute to project! ;-)