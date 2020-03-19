---
x-masysma-name: matrix_screensaver
section: 32
title: Matrix Screensaver
author: ["Linux-Fan, Ma_Sys.ma (Ma_Sys.ma@web.de)"]
keywords: ["matrix", "screensaver", "mdvl-matrix-screensaver", "resolution"]
date: 2019/12/21 00:31:23
lang: en-US
x-masysma-repository: https://www.github.com/m7a/bp-matrix-screensaver
x-masysma-website: https://masysma.lima-city.de/32/matrix_screensaver.xhtml
x-masysma-owned: 1
x-masysma-copyright: |
  Copyright (c) 2009, 2014, 2019 Ma_Sys.ma.
  For further info send an e-mail to Ma_Sys.ma@web.de.
---
Description
===========

_This program is not a production-quality screensaver._

This repository provides a basic Matrix-style screensaver which can be stopped
by pressing Enter. Additionally, script `matrix_screensaver_img` allows
images of this style to be generated e.g. for use as background images.

![Matrix Screensaver Sample Screenshot](matrix_screensaver_att/screenshot.png)

Note that this was mainly written for educatorial purposes. Today, it can serve
as a quick 2D graphics test. The code contains use for some advanced Java
features: Making a screenshot of the current display and changing the monitor's
resolution.

See also: `cmatrix(1)`

Invocation
==========

Start the screensaver with `matrix_screensaver -c`. If `-c` is left out, it
attempts to change the screen resolution to 640x480 (for additional
nostalgia...) while running the screensaver.

Start the image generation with `matrix_screensaver_img` to obtain file
`matrix-desktop.png`. Note that it only works for single-monitor use cases.
