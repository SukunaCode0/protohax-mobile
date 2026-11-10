cmake_minimum_required(VERSION 3.22.1)
project(modmenu)
set(CMAKE_CXX_STANDARD 17)
include(FetchContent)
FetchContent_Declare(
  imgui
  GIT_REPOSITORY https://github.com/ocornut/imgui.git
  GIT_TAG docking
)
FetchContent_MakeAvailable(imgui)
add_library(modmenu SHARED
  main.cpp
  GUI/ClickGUI.cpp
  Core/ModuleManager.cpp
)
target_include_directories(modmenu PRIVATE
  ${CMAKE_CURRENT_SOURCE_DIR}
  ${imgui_SOURCE_DIR}
  ${imgui_SOURCE_DIR}/backends
)
target_sources(modmenu PRIVATE
  ${imgui_SOURCE_DIR}/imgui.cpp
  ${imgui_SOURCE_DIR}/imgui_draw.cpp
  ${imgui_SOURCE_DIR}/imgui_tables.cpp
  ${imgui_SOURCE_DIR}/imgui_widgets.cpp
)
find_library(log-lib log)
find_library(android-lib android)
target_link_libraries(modmenu ${log-lib} ${android-lib} EGL GLESv3)
