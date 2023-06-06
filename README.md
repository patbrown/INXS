<p align="center">
  <a href="" rel="noopener">
 <img width=200px height=200px src="https://i.imgur.com/6wj0hh6.jpg" alt="Project logo"></a>
</p>

<h3 align="center">INXS</h3>

<div align="center">

  [![Status](https://img.shields.io/badge/status-active-success.svg)]() 
  [![GitHub Issues](https://img.shields.io/github/issues/patbrown/inxs.svg)](https://github.com/patbrown/inxs/issues)
  [![GitHub Pull Requests](https://img.shields.io/github/issues-pr/patbrown/inxs.svg)](https://github.com/patbrown/inxs/pulls)
  [![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE)

</div>

---

<p align="center"> Macros that make using interceptors easier without sacrificing callability.
    <br> 
</p>

## 📝 Table of Contents
- [What is it?](#what)
- [Why does it exist?](#why)
- [What does it cost?](#cost)
- [How do I use it?](#usage)
- [Are there examples?](#examples)
- [Is it finished?](#todo)
- [How can I help?](#contribute)
- [Did you show gratitude?](#gratitude)

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.patbrown/inxs.svg)](https://clojars.org/org.clojars.patbrown/inxs)

## What is it? <a name = "what"></a>
It provides a few public functions.
- ixfn creates named functions from interceptors and interceptors from anonymous fns.
- ls-ixfn lists all interceptor fns on the classpath.
- exec runs a chain by first finding and hydrating the interceptors that are referenced by keywords.
## Why does it exist? <a name = "why"></a>
Speed and simplicity for the user. It's a low-cost high-leverage change. Here are the benefits;
1. Interceptors and their non interceptor components are one in the same. IXFNs are prefixed `ixfn-your-symbol` while plain fns are simply `your-symbol`
2. Interceptors do not need to be required by namespace to be used, they simply need to be present on the classpath.
3. Chains can be constructed via keywords and not symbols. This makes them dead simple to construct chains programmatically at runtime. 
4. Using the `exec` fn an extra argument can be provided to control the return value. This extra argument is also simply a key or a collection to use with select keys further adding to the ability to programmatically control interceptors at runtime. 
## What does it cost? <a name = "cost"></a>
This simplicity and speed doesn't come free. Here are the negatives;
1. ixfn is a macro.
2. CLJS does not have `all-ns` which makes xplatform usage difficult since virtually all calls require an extra argument which is all the namespaces to search for ixfns. Because it just became awkward writing/reading/using it this way, I eventually dropped CLJS support. This was a painful decision for me, but I'd rather enjoy developing on one platform than not enjoy two.
3. Searching through all namespaces can be expensive in terms of time. 
## How do I use it? <a name = "usage"></a>
## Are there examples? <a name = "examples"></a>
## Is it finished? <a name = "todo"></a>
Yes.
## How can I help? <a name = "contribute"></a>
Use it, dream of a better future, and complain. This library does little and won't ever do much so...
## Did you show gratitude? <a name = "contribute"></a>
[Exoscale Interceptor](https://github.com/exoscale/interceptor) - It's the simplest interceptor library out there. It does little, asks for little, and reliably does what it says.
[Lambda Island Episode #32](https://github.com/lambdaisland/ep32testing/blob/master/src/ep32testing/core.clj) - This was the first time it hit me that I could store the var itself in the metadata attached to a var. I've learned painfully not to overuse it, but it's still a powerfully simple way to do cool stuff.
