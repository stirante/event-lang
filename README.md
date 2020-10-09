# EventLang

This is a simple POC language, which compiles to Minecraft Bedrock edition event.

## Requirements

 - Java 8 (or later)
 - Maven

## Building

```
git clone https://github.com/stirante/event-lang
cd event-lang
mvn clean package
```

## Usage

```
java -jar EventLang.jar <file>
```

```
 echo 'if has_tag == "yes" then add "yes" remove "no" end' | java -jar EventLang.jar
```

## Examples

```
if has_biome_tag == 'the_end' and other.has_tag == 'target' then
    add 'hostile_to_target_in_end'
end
if has_biome_tag != 'the_end' or other.has_tag != 'target' then
    remove 'hostile_to_target_in_end'
end
```

```
if has_biome_tag == 'the_end' then
    if other.has_tag == 'test1' then
        add 'test1'
        remove 'test2'
    end
    if other.has_tag == 'test2' then
        add 'test2'
        remove 'test1'
    end
end
```

## Syntax

I specifically created syntax to look like natural language, but also preserve logical conditions from programming languages.

Statement syntax:
```
if <conditions> then
    <actions and statements>
end
```

Action syntax:
```
add/remove <component_group_name>
```

Condition syntax:
```
[subject.]test <operator> <value>
```

Conditions can be joined using keywords `and`, `&&`, `or` and `||`. They can also be negated using keywords `not` and `!`.