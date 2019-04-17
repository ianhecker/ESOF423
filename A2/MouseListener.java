        MouseListener mouseListener = new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if(JFtp.uiBlocked)
                {
                    return;
                }

                if(e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e))
                {
                    int index = jl.getSelectedIndex() - 1;

                    if(index < -1)
                    {
                        return;
                    }

                    String tgt = (String) jl.getSelectedValue().toString();

                    if(index < 0)
                    {
                    }
                    else if((dirEntry == null) || (dirEntry.length < index) ||
                                (dirEntry[index] == null))
                    {
                        return;
                    }
                    else
                    {
                        currentPopup = dirEntry[index];
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

            public void mouseClicked(MouseEvent e)
            {
                if(JFtp.uiBlocked)
                {
                    return;
                }
                
        		TableUtils.copyTableSelectionsToJList(jl, table);

                //System.out.println("DirEntryListener::");
                if(e.getClickCount() == 2)
                {
                    //System.out.println("2xList selection: "+jl.getSelectedValue().toString());
                    int index = jl.getSelectedIndex() - 1;

                    // mousewheel bugfix
                    if(index < -1)
                    {
                        return;
                    }

                    String tgt = (String) jl.getSelectedValue().toString();

                    if(index < 0)
                    {
                        doChdir(path+tgt);
                    }
                    else if((dirEntry == null) || (dirEntry.length < index) ||
                                (dirEntry[index] == null))
                    {
                        return;
                    }
                    else if(dirEntry[index].isDirectory())
                    {
                       doChdir(path+tgt);
                    }
                    else if(dirEntry[index].isLink())
                    {
                        if(!con.chdir(path + tgt))
                        {
                            showContentWindow(path +
                                              dirEntry[index].toString(),
                                              dirEntry[index]);

                            //blockedTransfer(index);
                        }
                    }
                    else
                    {
                        showContentWindow(path + dirEntry[index].toString(),
                                          dirEntry[index]);

                        //blockedTransfer(index);
                    }
                }
            }
        };